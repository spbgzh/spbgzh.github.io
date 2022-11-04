<template>
  <div>
    <n-card
      hoverable
      :segmented="{
        content: true,
        footer: 'soft',
      }"
    >
      <Markdown :source="data.content" />
      <template #footer>
        @Github Repository Address: <a :href="data.url">{{ data.url }}</a>
      </template>
    </n-card>
  </div>
</template>

<script lang="ts">
import Markdown from "vue3-markdown-it";
import axios from "axios";

const getReadme = (url: string) => {
  return axios.create()({
    url,
    method: "Get",
  });
};

const data = reactive({
  content: "",
  url: "",
});

export default defineComponent({
  components: {
    Markdown,
  },
  setup() {
    onMounted(() => {
      const meta = useRoute().meta;
      const repository: string = meta.repository as string;
      try {
        getReadme(
          "https://api.github.com/repos/" + repository + "/readme"
        ).then((res) => {
          data.content = decodeURIComponent(
            Array.prototype.map
              .call(atob(res.data.content), function (c) {
                return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
              })
              .join("")
          );
          data.url = "https://github.com/" + repository;
        });
      } catch (e) {
        console.log(e);
      }
    });
    return { data };
  },
});
</script>

<style scoped></style>

<template>
  <div>
    <n-config-provider :theme-overrides="themeOverrides">
      <n-space vertical>
        <n-layout>
          <n-layout-header bordered>
            <n-row gutter="24">
              <n-col :span="5">
                <div align="center" class="logo">
                  <n-icon-wrapper
                    :size="70"
                    :border-radius="50"
                    color="{{colorData.color}}"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      xmlns:xlink="http://www.w3.org/1999/xlink"
                      viewBox="0 0 512 512"
                    >
                      <path
                        d="M256 256s-48-96-126-96c-54.12 0-98 43-98 96s43.88 96 98 96c37.51 0 71-22.41 94-48"
                        fill="none"
                        stroke="currentColor"
                        stroke-linecap="round"
                        stroke-miterlimit="10"
                        stroke-width="22"
                      ></path>
                      <path
                        d="M256 256s48 96 126 96c54.12 0 98-43 98-96s-43.88-96-98-96c-37.51 0-71 22.41-94 48"
                        fill="none"
                        stroke="currentColor"
                        stroke-linecap="round"
                        stroke-miterlimit="10"
                        stroke-width="22"
                      ></path>
                    </svg>
                  </n-icon-wrapper>
                </div>
              </n-col>
              <n-col :span="15">
                <div align="center">
                  <n-gradient-text
                    gradient="linear-gradient(90deg, #1db3c8 0%,#1d1dc8 50%, #b31dc8 100%)"
                  >
                    This is HomePage of Spbgzh
                  </n-gradient-text>
                </div>
              </n-col>
              <n-col :span="4" align="center">
                <n-switch
                  v-model:value="disabled"
                  size="large"
                  style="padding: 22px 0 0 70%"
                /><br />
              </n-col>
            </n-row>
          </n-layout-header>
          <n-layout has-sider>
            <n-layout-sider
              bordered
              show-trigger
              collapse-mode="width"
              :collapsed-width="64"
              :width="300"
              :native-scrollbar="false"
              :inverted="inverted"
              style="min-height: calc(100vh - 115px)"
            >
              <n-menu
                :collapsed-width="64"
                :collapsed-icon-size="22"
                :options="menuOptions"
                default-value="about-me"
                watch-props="defaultValue"
                :default-expanded-keys="defaultExpandedKeys"
              />
            </n-layout-sider>
            <n-layout-content content-style="padding: 24px;">
              <router-view
                :key="($route.meta.repository as string)"
              ></router-view>
            </n-layout-content>
            <n-layout-sider
              content-style="padding: 24px; padding-left: 5px"
              :width="40"
            >
              <n-slider
                v-model:value="value"
                vertical
                reverse
                :step="1"
                :tooltip="false"
              >
                <template #thumb>
                  <n-icon-wrapper :size="24" :border-radius="12">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      xmlns:xlink="http://www.w3.org/1999/xlink"
                      viewBox="0 0 512 512"
                    >
                      <path
                        d="M256 256s-48-96-126-96c-54.12 0-98 43-98 96s43.88 96 98 96c37.51 0 71-22.41 94-48"
                        fill="none"
                        stroke="currentColor"
                        stroke-linecap="round"
                        stroke-miterlimit="10"
                        stroke-width="22"
                      ></path>
                      <path
                        d="M256 256s48 96 126 96c54.12 0 98-43 98-96s-43.88-96-98-96c-37.51 0-71 22.41-94 48"
                        fill="none"
                        stroke="currentColor"
                        stroke-linecap="round"
                        stroke-miterlimit="10"
                        stroke-width="22"
                      ></path>
                    </svg>
                  </n-icon-wrapper>
                </template>
              </n-slider>
            </n-layout-sider>
          </n-layout>
          <n-layout-footer bordered>
            <div align="center">© 2022 Spbgzh</div>
          </n-layout-footer>
        </n-layout>
      </n-space>
    </n-config-provider>
  </div>
</template>

<script lang="ts">
import { NIcon, MenuOption } from "naive-ui";
import type { Component } from "vue";
import {
  BookOutline as BookIcon,
  PersonOutline as PersonIcon,
  ChatbubbleEllipsesOutline,
  GlobeOutline,
  InfiniteOutline,
  SchoolOutline,
  HeartOutline,
  HardwareChipOutline,
  PulseOutline,
  BluetoothOutline,
  FolderOutline,
  ShareSocialOutline,
  GameControllerOutline,
  AppsOutline,
  AnalyticsOutline,
  HappyOutline,
  GitMergeOutline,
  HomeOutline,
} from "@vicons/ionicons5";

function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) });
}
const changeColor = (value: number) => {
  let str = "#";
  let temp = 29;
  if (value >= 0 && value <= 50) {
    temp = 29 + 3 * (50 - value);
    str = str + "1d" + temp.toString(16) + "c8";
    themeOverrides.common.primaryColor = str;
    themeOverrides.Slider.railColorHover = str;
    themeOverrides.Slider.fillColorHover = str;
    colorData.color = str;
  } else {
    temp = 29 + 3 * (value - 50);
    str = str + temp.toString(16) + "1d" + "c8";
    themeOverrides.common.primaryColor = str;
    themeOverrides.Slider.railColorHover = str;
    themeOverrides.Slider.fillColorHover = str;
    colorData.color = str;
  }
};
const menuOptions: MenuOption[] = [
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "about-me",
          },
        },
        { default: () => "About Me" }
      ),
    key: "about-me",
    icon: renderIcon(PersonIcon),
  },
  {
    label: "Projects",
    key: "my-projects",
    icon: renderIcon(BookIcon),
    children: [
      {
        label: "Learning Project",
        key: "learning-resources",
        icon: renderIcon(SchoolOutline),
        children: [
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "neurotechnology-and-programming",
                  },
                },
                { default: () => "Neurotechnology" }
              ),
            key: "neurotechnology-and-programming",
            icon: renderIcon(FolderOutline),
          },
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "ai-system",
                  },
                },
                { default: () => "AI System" }
              ),
            key: "ai-system",
            icon: renderIcon(AnalyticsOutline),
          },
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "leanring-neural-network",
                  },
                },
                { default: () => "Leanring Neural Network" }
              ),
            key: "leanring-neuralNetwork",
            icon: renderIcon(ShareSocialOutline),
          },
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "graph-theory",
                  },
                },
                { default: () => "Graph Theory" }
              ),
            key: "graph-theory",
            icon: renderIcon(GitMergeOutline),
          },
        ],
      },
      {
        label: "Web Project",
        key: "web-project",
        icon: renderIcon(GlobeOutline),
        children: [
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "chatex",
                  },
                },
                { default: () => "Chatex" }
              ),
            key: "chatex",
            icon: renderIcon(InfiniteOutline),
          },
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "adopt-animal",
                  },
                },
                { default: () => "AdoptAnimal" }
              ),
            key: "adopt-animal",
            icon: renderIcon(HeartOutline),
          },

          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "home-page",
                  },
                },
                { default: () => "HomePage" }
              ),
            key: "home-page",
            icon: renderIcon(HomeOutline),
          },
        ],
      },
      {
        label: "Neurotechnology Project",
        key: "neurotechnology-project",
        icon: renderIcon(PulseOutline),
        children: [
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "eeg-emotion-recognition",
                  },
                },
                { default: () => "Emotion Recognition" }
              ),
            key: "eeg-emotion-recognition",
            icon: renderIcon(HappyOutline),
          },
        ],
      },
      {
        label: "Embedded Project",
        key: "embedded-project",
        icon: renderIcon(HardwareChipOutline),
        children: [
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "bluetooth-car",
                  },
                },
                { default: () => "Bluetooth Car" }
              ),
            key: "bluetooth-car",
            icon: renderIcon(BluetoothOutline),
          },
        ],
      },
      {
        label: "Application Project",
        key: "Application-project",
        icon: renderIcon(AppsOutline),
        children: [
          {
            label: () =>
              h(
                RouterLink,
                {
                  to: {
                    name: "ai-gobang",
                  },
                },
                { default: () => "AI GoBang" }
              ),
            key: "ai-gobang",
            icon: renderIcon(GameControllerOutline),
          },
        ],
      },
    ],
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "contact-me",
          },
        },
        { default: () => "Contact me" }
      ),
    key: "contact-me",
    disabled: false,
    icon: renderIcon(ChatbubbleEllipsesOutline),
  },
];
const colorData = reactive({
  color: "#1d1dc8",
});
const themeColor = "#1d1dc8";
const themeOverrides = reactive({
  common: {
    primaryColor: themeColor,
  },
  Slider: {
    railColor: "#FFFFFF",
    fillColor: "#FFFFFF",
    railColorHover: themeColor,
    fillColorHover: themeColor,
  },
});
const value = ref(50);
let flag = true;
const disabled = ref(true);
const addNum = () => {
  if (value.value === 0) {
    flag = false;
  }
  if (value.value === 100) {
    flag = true;
  }
  if (flag) {
    value.value = value.value - 1;
  }
  if (!flag) {
    value.value = value.value + 1;
  }
};
let timer: number;
export default defineComponent({
  setup() {
    watch(value, (value, oldValue) => {
      if (value !== oldValue) {
        changeColor(value);
      }
    });
    watch(disabled, (value, oldValue) => {
      if (value !== oldValue) {
        if (value === true) {
          timer = window.setInterval(() => {
            setTimeout(addNum, 0);
          }, 70);
        } else {
          setTimeout(() => {
            clearInterval(timer);
          }, 0);
        }
      }
    });
    timer = window.setInterval(() => {
      setTimeout(addNum, 0);
    }, 70);
    return {
      inverted: ref(false),
      menuOptions,
      themeOverrides,
      value,
      InfiniteOutline,
      defaultExpandedKeys: ["my-projects"],
      colorData,
      disabled,
    };
  },
});
</script>

<style scoped>
.logo {
  padding: 2px 0 0 0;
}
.n-gradient-text {
  font-size: 40px;
  padding: 10px 0 0 80px;
}
</style>

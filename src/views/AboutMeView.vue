<template>
  <div>
    <n-h1>Introduction</n-h1>
    <n-p>
      Hi,I'am Guo Zihan. Currently a third-year student at ITMO University's
      University of Neurotechnology and Software Engineering.
    </n-p>
    <n-divider />
    <n-h1>Work Experience</n-h1>
    <n-row gutter="23">
      <n-col :span="11">
        <n-h3
          >Wuque Studio (2020 - Present)
          <n-tag size="small" type="success">
            Embedded Development
            <template #icon>
              <n-icon :component="HardwareChipOutline" />
            </template>
          </n-tag>
        </n-h3>

        <n-p>
          Keyboard firmware development based on open source project
          <a href="https://github.com/qmk">QMK</a><br />
          Developed keyboards:
          <n-ul>
            <n-li>ikki68 aurora</n-li>
            <n-li>Mammoth75/20</n-li>
            <n-li>Promise87</n-li>
            <n-li>Zoom65</n-li>
            <n-li>Zoom87</n-li>
          </n-ul>
        </n-p>
      </n-col>
      <n-col :span="12">
        <n-carousel autoplay show-arrow interval="3000">
          <img
            class="carousel-img"
            src="https://cdn.shopify.com/s/files/1/0403/9809/4489/collections/AuroraR2_Snow05_1024x1024_f0191ef3-614a-4a08-aede-4e2350b5b038_2048x.png?v=1636647077"
          />
          <img
            class="carousel-img"
            src="https://cdn.shopify.com/s/files/1/0403/9809/4489/collections/Mammoth75_-_Imgur_2048x.jpg?v=1639385928"
          />
          <img
            class="carousel-img"
            src="https://cdn.shopify.com/s/files/1/0607/2474/0287/files/2_1024x1024.png?v=1663309806"
          />
          <img
            class="carousel-img"
            src="https://images.ctfassets.net/pz45c6yy2sav/2F1GeO2my2wzPXLEEwfsMj/713aa639e5bae9a7155ef9f6e7e09b01/unknown.png"
          />
          <template #arrow="{ prev, next }">
            <div class="custom-arrow">
              <button type="button" class="custom-arrow--left" @click="prev">
                <n-icon>
                  <ArrowBack />
                </n-icon>
              </button>
              <button type="button" class="custom-arrow--right" @click="next">
                <n-icon>
                  <ArrowForward />
                </n-icon>
              </button>
            </div>
          </template>
          <template #dots="{ total, currentIndex, to }">
            <ul class="custom-dots">
              <li
                v-for="index of total"
                :key="index"
                :class="{ ['is-active']: currentIndex === index - 1 }"
                @click="to(index - 1)"
              ></li>
            </ul>
          </template>
        </n-carousel>
      </n-col>
    </n-row>
    <n-divider />
    <n-h1>Skills</n-h1>
    <n-row gutter="23">
      <n-col :span="11">
        <n-p
          ><b>Programming: </b>Python, c/c++, Java, Swift, HTML/Javascript,
          SQL</n-p
        >
        <n-p><b>Web: </b>Vue, Node.js, Spring boot, Bootstrap</n-p>
        <n-p><b>Database: </b>MongoDB, Mysql, Neo4j, PostgreSQL</n-p>
      </n-col>
      <n-col :span="12">
        <n-p
          ><b>Currently Studying: </b>Artificial intelligence algorithm,
          brain-computer interface</n-p
        >
        <n-p
          ><b>Future Study Plan: </b>I'm going to continue to learn EEG signal
          recognition. If I really can't find a job, I will become a front-end
          programmer, as long as I can ensure my own food and drink.</n-p
        >
      </n-col>
    </n-row>
  </div>
</template>

<script lang="ts">
import {
  ArrowBack,
  ArrowForward,
  HardwareChipOutline,
} from "@vicons/ionicons5";

export default defineComponent({
  components: {
    ArrowBack,
    ArrowForward,
  },
  setup() {
    return {
      HardwareChipOutline,
    };
  },
});
</script>

<style scoped>
.carousel-img {
  width: 100%;
  height: 270px;
  object-fit: cover;
}

.custom-arrow {
  display: flex;
  position: absolute;
  bottom: 25px;
  right: 10px;
}

.custom-arrow button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 28px;
  height: 28px;
  margin-right: 12px;
  color: #fff;
  background-color: rgba(255, 255, 255, 0.1);
  border-width: 0;
  border-radius: 8px;
  transition: background-color 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  cursor: pointer;
}

.custom-arrow button:hover {
  background-color: rgba(255, 255, 255, 0.2);
}

.custom-arrow button:active {
  transform: scale(0.95);
  transform-origin: center;
}

.custom-dots {
  display: flex;
  margin: 0;
  padding: 0;
  position: absolute;
  bottom: 20px;
  left: 20px;
}

.custom-dots li {
  display: inline-block;
  width: 12px;
  height: 4px;
  margin: 0 3px;
  border-radius: 4px;
  background-color: rgba(255, 255, 255, 0.4);
  transition: width 0.3s, background-color 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  cursor: pointer;
}

.custom-dots li.is-active {
  width: 40px;
  background: #fff;
}
</style>

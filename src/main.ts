import { createApp } from "vue";
import "highlight.js/styles/monokai.css";

import App from "./App.vue";
import router from "./router";
import naive from "naive-ui";
import "vfonts/Lato.css";
import "vfonts/FiraCode.css";

const app = createApp(App);

app.use(router).use(naive).mount("#app");

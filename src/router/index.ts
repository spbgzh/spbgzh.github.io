import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      redirect: "about-me",
      children: [
        {
          path: "about-me",
          name: "about-me",
          meta: {
            title: "About Me",
          },
          component: async () => await import("../views/AboutMeView.vue"),
        },
        {
          path: "contact-me",
          name: "contact-me",
          meta: {
            title: "Contact Me",
          },
          component: async () => await import("../views/ContactMeView.vue"),
        },
        {
          path: "project",
          name: "project",
          meta: {
            title: "project",
          },
          children: [
            {
              path: "neurotechnology-and-programming",
              name: "neurotechnology-and-programming",
              meta: {
                repository: "spbgzh/Neurotechnology-And-Programming-Courses",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "ai-system",
              name: "ai-system",
              meta: {
                repository: "spbgzh/artificial-intelligence-system",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "leanring-neural-network",
              name: "leanring-neural-network",
              meta: {
                repository: "spbgzh/LearningNeuralNetworks",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "graph-theory",
              name: "graph-theory",
              meta: {
                repository: "spbgzh/graph-theory-algorithm",
              },

              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "emotion-recognition",
              name: "emotion-recognition",
              meta: {
                repository: "DenisAndGzh/emotion-recognition",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "chatex",
              name: "chatex",
              meta: {
                repository: "spbgzh/chatex",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "adopt-animal",
              name: "adopt-animal",
              meta: {
                repository: "DenisAndGzh/adoptanimal",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "home-page",
              name: "home-page",
              meta: {
                repository: "spbgzh/spbgzh.github.io",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "bluetooth-car",
              name: "bluetooth-car",
              meta: {
                repository: "spbgzh/BluetoothCar",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
            {
              path: "ai-gobang",
              name: "ai-gobang",
              meta: {
                repository: "spbgzh/AI-GoBang",
              },
              component: async () => await import("../views/ProjectView.vue"),
            },
          ],
        },
      ],
    },
  ],
});

export default router;

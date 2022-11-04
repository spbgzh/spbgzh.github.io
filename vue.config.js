/* eslint-disable quote-props */
/* eslint-disable @typescript-eslint/no-var-requires */
module.exports = {
  configureWebpack: {
    plugins: [
      require("unplugin-auto-import/webpack")({
        eslintrc: {
          enabled: true,
        },
        imports: [
          "vue",
          "vue-router",
          {
            "vue-router": ["RouterLink"],
            axios: [["default", "axios"]],
          },
        ],
        dts: true,
      }),
    ],
  },
  publicPath: '/',
  runtimeCompiler: true,
};

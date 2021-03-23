module.exports = {
  devServer: {
    proxy: {
      "^/rest": {
        target: "http://localhost:8081",
        ws: false,
        changeOrigin: false,
      },
      "^/login": {
        target: "http://localhost:8081",
        ws: false,
        changeOrigin: false,
      },
    },
  },
};

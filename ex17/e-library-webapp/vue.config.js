module.exports = {
    devServer: {
        proxy: {
            "^/rest": {
                target: "http://localhost:8081",
                ws: true,
                changeOrigin: true
            }
        },
    }
};

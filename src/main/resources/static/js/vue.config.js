module.exports = {
    /** 서버 설정 */
    devServer: {
        port: 3000,
        proxy: {
            '/api/*': {
                target: 'http://localhost:8080'
            }
        }
    }
}
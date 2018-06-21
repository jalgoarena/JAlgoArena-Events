module.exports = {
    apps: [
        {
            name: 'events',
            args: [
                "-jar",
                "build/libs/jalgoarena-events-2.1.0-SNAPSHOT.jar"
            ],
            script: 'java',
            env: {
                PORT: 5005,
                BOOTSTRAP_SERVERS: 'localhost:9092,localhost:9093,localhost:9094'
            }
        }
    ]
};

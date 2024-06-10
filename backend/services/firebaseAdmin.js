const serviceAccount = require('../firebaseKey.json');


class FireBaseManager {

    constructor() {
        if(!FireBaseManager.instance){
            this.admin = require('firebase-admin');
            this.admin.initializeApp({
                credential: this.admin.credential.cert(serviceAccount)
            });
            FireBaseManager.instance = this;
        }
        return FireBaseManager.instance;
    }

    async sendNotificationToUser(message) {
        this.admin.messaging().send(message).then((response) => {
            // Response is a message ID string.
            console.log('Successfully sent message from firebase!');
          })
          .catch((error) => {
            console.log('Error sending message:', error);
          });
     }


}

module.exports = FireBaseManager;
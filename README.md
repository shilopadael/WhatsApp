

<h1 align="center">
Chat-System
</h1>

<h4 align="center">
Stay Connected, Chat with Ease!
</h4>

### About
Welcome to the Chat App GitHub project! This project aims to provide a seamless communication experience through a feature-rich chat application. It utilizes various technologies to deliver real-time messaging across multiple platforms.

### Technologies Used

* **Frontend**: The frontend is developed using **React**, a popular JavaScript library for building user interfaces. It ensures a dynamic and user-friendly web interface.

* **Backend**: The backend of the application is powered by Express, a flexible and minimalist web application framework for _Node.js_. **Express** handles server-side operations, enabling efficient request handling and response generation.

* **Real-time Communication**: The chat app leverages _Socket.io_, a JavaScript library that enables real-time, bidirectional communication between the client and server. It facilitates instant message updates and push notifications across devices.

* **Database**: The application relies on **MongoDB**, a scalable and flexible _NoSQL database_. MongoDB efficiently stores and retrieves messages and user data, ensuring a reliable and seamless chatting experience.

* **Android App**: In addition to the web version, the Chat App includes an Android application developed using Java. The Android app utilizes **Firebase Cloud Messaging** for push notifications, enabling users to receive chat notifications directly on their mobile devices.

### Installation

* Cloning the project:
```
git clone https://github.com/shilopadael/Advance-Programing-Project-2
```


Inside the project main folder to:

* Starting the server:
```
npm run server
```

* Start the android studio:





### Server file configuration

The default server port is 5000 for http reqeust and at 5001 for socket.io.
To change the the port go to the ./backend/config/.env.local

The default config include our mongodb account online if you don't wish to change it, otherwise you can set new config in the .env.local file
```
CONNECTION_STRING="mongodb://localhost:<port>"
PORT = 5000
```

* CONNECTION_STRING - is for the mongodb interface
* PORT - the http port that the sever listen, note that the socket.io is set up to be PORT + 1

### React file configuration

The default config file for the react is inside the ./frontend/src/services/api.js

the default is at port 5000 for http reqeust and at 5001 for socket.io
```
const PORT = 5000;
const PORT_WS = 5001;
```

* PORT - the server http request port
* PORT_WS - the server socket.io port

**make sure after you changed the react file you need to rebuild the project and to pass it as public files to the server public folder.**

* For your convince we created a script for that inside the main folder of the repository: **(for Linux shell only)**
```
npm run build-server
```

* For **Windows**:

```
cd frontend
npm run build

~~~ copy the content inside frontend/build to backend/public ~~~

```

### Contributors

* Omer Bar (Computer Science Student, Second Year)
* Shilo Padael (Computer Science Student, Second Year)

We, Omer Bar and Shilo Padael, both second-year computer science students, have collaborated closely on the development of this project. Our combined efforts and expertise have been instrumental in shaping and enhancing the Chat App. With a shared passion for creating innovative solutions, we have dedicated ourselves to delivering a high-quality and user-friendly chat application.

Throughout the development process, we have worked hand in hand, brainstorming ideas, implementing features, and ensuring the smooth integration of various technologies. Our commitment to excellence, attention to detail, and strong problem-solving skills have driven us to overcome challenges and continuously improve the application.

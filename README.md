<h1>Creating a WhatsApp Web Application with Real-Time Messaging</h1>

<h1>Milestone: "Full-Stack Chat Application with Real-Time Messaging"</h1>
<h1>ex1 part 1 is in BRANCH ex1 (HTML, CSS)</h1>
<h1>ex1 part 2 is in BRANCH ex2 (REACT)</h1>
<h1>ex2 is in BRANCH ex3  (BACKEND SIDE)</h1>
<h1>ex3 is in BRANCH ex4 (ANDROID APP)</h1>

<h2>Features</h2>
<p>We have created:</p>
<ul>
  <li>A server using the MVC architecture that supports a specific API.</li>
  <li>Frontend development using React, which is integrated with the server.</li>
  <li>MongoDB as the underlying database module.</li>
  <li>Real-time messaging using WebSockets:</li>
  <ul>
    <li>When user X sends a message to user Y on the same website, user Y will receive an instant message.</li>
    <li>An alert will appear in the user interface of user Y.</li>
    <li>The server is capable of pushing messages immediately to user Y, although user Y still needs to request them.</li>
  </ul>
  <li>The socket.io connection is updating the client live! When a new client successfully logs into the chat page, it holds a socket connection to the server's socket.io port (by default is 5001). This connection will update the client when:</li>
  <ul>
    <li>A new message is received.</li>
    <li>A new contact is added (the user that you added will update the chat list).</li>
    <li>Upon deleting a contact, it will delete the chat from both contacts.</li>
  </ul>
</ul>


<h2>Technologies Used</h2>

<ul>
  <li>Node.js</li>
  <li>Express.js</li>
  <li>React</li>
  <li>WebSocket</li>
  <li>MongoDB</li>
  <li>HTML</li>
  <li>CSS</li>
  <li>JavaScript</li>
</ul>

<h2>Updated Features and Implementation</h2>

<h3>Navigation</h3>
<p>
    Implement React Router to handle navigation between different pages: login, registration, and chat.<br>
    Set up routes for each page and configure the necessary components to render.
</p>

<h3>Login Page</h3>
<p>
    Create a Login component that includes a form for users to enter their login credentials.<br>
    Handle form submission and authentication logic using React's state management.
</p>
<image src="frontend/public/ReadmeImages/LoginImage.png" style="width:60%">

<h3>Registration Page</h3>
<p>
    Create a Registration component that includes a form for users to provide their personal information and register a new account.<br>
    Handle form submission and user registration logic using React's state management.
</p>
<image src="frontend/public/ReadmeImages/RegisterImage.png" style="width:60%">

<h3>Main Chat Interface</h3>
<p>
    Design and implement the main chat interface using React components.<br>
    Fetch and display user conversations and messages using API calls or mock data.<br>
    Implement features like creating new chats, participating in existing chats, and sending messages.
</p>
<image src="frontend/public/ReadmeImages/ChatImage.png" style="width:60%">

<h1>Installation</h1>
<p>Note: be patience when the server send request, because of the free mongoDB database the reads and writes to the database are slow!</p>
<ol>
	<li>Clone the repository to your local machine:
	<br><code>git clone https://github.com/shilopadael/Advance-Programing-Project-2</code>
	</li>
</ol>
	
<ol start="2">
	<li>Navigate to the project directory:<br>
		<code>cd Advance-Programing-Project-2</code>
	</li>
</ol>
<ol start="3">
	<li>Install the backend packages:<br>
		<code>cd backend && npm i</code>
	</li>
</ol>
<ol start="4">
	<li>Install the frontend packages:<br>
		<code>cd frontend && npm i</code>
	</li>
</ol>
<ol start="5">
	<li>Start The Server<br>
    <p>Here you will need to set up your database connection string at mongoDB. Go to the ./config folder and edit the file .env.local.
    CONNECTION_STRING should be the mongoDB database connection string url.
    the PORT should be the port that the server is going to listen to.</p>
		<code>by default the port of the express is 5000 and the socket.io is 5001</code>
    <p>save the file and run:</p>
    <code>npm start</code>
    <p>at the /backend folder</p>
	</li>
</ol>
<ol start="6">
	<li>Start The Client<br>
    <p>There is two ways:</p>
		<code>1. from static file (served from the server)</code>
    <p>Just go to the server ip address and port</p>
    <code>on your computer is http://localhost/5000</code><br>
    <code>2. from a react app</code>
    <p>go to the folder /frontend and execute:</p>
    <code>npm start</code><br>
    <p>If you changed the port (at the server .env.local) you need to modify the react app! the react app is setup for port 5000 at express and 5001 at socket.io</p>
    <p>to change the express port go to:</p>
    <code> /frontend/src/services/api.js</code>
    <p>and to change the socket.io port go to: </p>
    <code>/frontend/src/components/ChatPage/Chat.js</code>
    <p>and change the port from each of them</p>
	</li>
</ol>
	
<h1>Credits</h1>
<p>The project was built by Shilo Padael and Omer Bar, with the help of online resources and tutorials.</p>

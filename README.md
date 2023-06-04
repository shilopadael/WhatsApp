
<h1>creating an MVC server using express, http and websockets!</h1>

<h1>Milestone: "Full-Stack Chat Application with Real-Time Messaging"</h1>

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


<h1>Installation</h1>
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
	<li>Start Server<br>
    <p>Here you will need to set up your database connection string at mongoDB. Go to the ./config folder and edit the file .env.local.
    CONNECTION_STRING should be the mongoDB database connection string url
    port should be the port of the server is going to listen to</p>
		<code>by default the port of the express is 5000 and the socket.io is 5001</code>
    <p>save the file and run:</p>
    <code>npm start</code>
    <p>at the /backend folder</p>
	</li>
</ol>
	
<h1>Credits</h1>
<p>The project was built by Shilo Padael and Omer Bar, with the help of online resources and tutorials.</p>

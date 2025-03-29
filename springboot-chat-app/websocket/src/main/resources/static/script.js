var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);
var username = "";

stompClient.connect({}, function(frame) {
    console.log("Connected: " + frame);

    // Subscribe to messages
    stompClient.subscribe('/topic/return-message', function(response) {
        var data = JSON.parse(response.body);
        showMessage(data.username, data.message);
    });

    // Subscribe to user join notifications
    stompClient.subscribe('/topic/user-joined', function(response) {
        var data = JSON.parse(response.body);
        showUserJoined(data.username);
    });
    
stompClient.subscribe('/topic/user-left', function(response) {
    var data = JSON.parse(response.body);
    showUserLeft(data.username);
});
});

// Set Username & Notify Backend
function setUsername() {
    var input = document.getElementById("username");
    if (input.value.trim() !== "") {
        username = input.value.trim();
        document.getElementById("usernameContainer").classList.add("hidden");
        document.getElementById("chatContainer").classList.remove("hidden");

        // Notify others that a new user has joined
        stompClient.send("/app/user-joined", {}, JSON.stringify({ username }));
    }
}

// Send message function
function sendMessage() {
    var messageInput = document.getElementById("message");
    var message = messageInput.value.trim();
    
    if (message !== "" && username !== "") {
        stompClient.send("/app/message", {}, JSON.stringify({ username, message }));
        messageInput.value = "";
    }
}

// Display chat messages
function showMessage(sender, message) {
    var messageDiv = document.getElementById("messages");
    var div = document.createElement("div");

    div.textContent = `${sender}: ${message}`;
    div.className = `p-2 my-1 rounded-lg text-sm w-fit max-w-xs 
        ${sender === username ? "bg-blue-500 text-white self-end ml-auto" : "bg-gray-300 text-black self-start mr-auto"}`;

    messageDiv.appendChild(div);
    messageDiv.scrollTop = messageDiv.scrollHeight;
}

// Display "User Joined" Notification
function showUserJoined(user) {
    var messageDiv = document.getElementById("messages");
    var div = document.createElement("div");

    div.textContent = `${user} joined the chat`;
    div.className = "text-center text-gray-500 text-xs italic my-2";

    messageDiv.appendChild(div);
    messageDiv.scrollTop = messageDiv.scrollHeight;
}

// Function to display "User Left" message
function showUserLeft(username) {
    var messageDiv = document.getElementById("messages");
    var p = document.createElement("p");
    p.textContent = username + " has left the chat.";
    p.className="text-center text-xs italic my-2";
    p.style.color = "red";
    messageDiv.appendChild(p);
}

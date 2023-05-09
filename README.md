# DiscordChatGPTBot

Project to create a Discord bot that communicates with ChatGPT. This project was created only as a training to use the Discord and OpenAI APIs using Java.

The project is simple, it consists of using JDA (Java Discord API) to read commands inserted in a chat from a Discord server and direct the text to ChatGPT using the OpenAI API, to later receive the response from ChatGPT and send this answer back to the same chat on the Discord server.

## Commands
To call this bot in a chat, the expression *"!xinxila"* was chosen as the keyword. This keyword must be followed by commands to perform an action.

This bot has only two commands: *"ping"* and *"chatgpt"*.

### ping
The *ping* command will just return the text "Pong!" in the Discord chat. A prank that serves to check if the bot is working.

Example:
```bash
!xinxila ping
```

### chatgpt
The *chatgpt* command must be followed by a text that will be sent to OpenAI and processed by ChatGPT, then the return of this processing will be forwarded back to the discord chat.

Example:
```bash
!xinxila chatgpt write lorem ipsum
```
# Very Important Observation
This bot's nickname is ***ChatGPT Xinxila's Bot***

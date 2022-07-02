package com.telegram.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SendMessageOperationService {
	
	private final String greetingMessage = "Привет!";
	private final String yourUserName = "Ваше имя: ";
	private final String yourUserId = "Ваш уникальный ID: ";
	
	public SendMessage createGreetingInformation(Update update) {
		return createSimpleMessage(update, greetingMessage);
	}
	
	public SendMessage sendUserFullName(Update update) {
		return userFullName(update, yourUserName);
	}
	
	public SendMessage sendUserName(Update update) {
		return userName(update, yourUserName);
	}
	
	public SendMessage sendUserId(Update update) {
		return userId(update, yourUserId);
	}
	
	private SendMessage userId(Update update, String message) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
		sendMessage.setText(message + update.getMessage().getChat().getId());	
		return sendMessage;
	}
	
	private SendMessage userName(Update update, String message) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
		sendMessage.setText(message + update.getMessage().getChat().getUserName());	
		return sendMessage;
	}
	
	private SendMessage userFullName(Update update, String message) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
		sendMessage.setText(message + update.getMessage().getChat().getFirstName() + " " + update.getMessage().getChat().getLastName());	
		return sendMessage;
	}

	private SendMessage createSimpleMessage(Update update, String message) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
		sendMessage.setText(message);
		return sendMessage;
	}
}

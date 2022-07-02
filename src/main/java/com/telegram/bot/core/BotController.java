package com.telegram.bot.core;

import static com.telegram.bot.constant.VarConst.*;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.telegram.bot.service.SendMessageOperationService;

@Component
public class BotController extends TelegramLongPollingBot {

	SendMessageOperationService sendMessageOperationService = new SendMessageOperationService();

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {

			switch (update.getMessage().getText()) {
			case START:
				executeMessage(sendMessageOperationService.createGreetingInformation(update));
				break;
			case LK:
				if (update.getMessage().getChat().getFirstName() != null
						&& update.getMessage().getChat().getLastName() != null) {
					executeMessage(sendMessageOperationService.sendUserFullName(update));
				} else {
					executeMessage(sendMessageOperationService.sendUserName(update));
				}
				executeMessage(sendMessageOperationService.sendUserId(update));
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "nimikinbot";
	}

	@Override
	public String getBotToken() {
		return "5148826823:AAFkopHQMo7394w0RLVemYtPJCy82dbl1B0";
	}

	private <T extends BotApiMethod> void executeMessage(T sendMessage) {
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			System.out.println("Не удалось открыть сообщение" + e.getCause());
		}
	}

}

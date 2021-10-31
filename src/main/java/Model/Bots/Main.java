package Model.Bots;

import View.BotManager.BotManagerFrame;

public class Main {
	/**
	 * Construct and start a botManager
	 * @param args
	 */
	public static void main(String[] args){
		BotManager botManager = new BotManager();
		botManager.start();

		new BotManagerFrame(botManager);
	}
}

package ro.deiutzblaxo.bank.config;

public enum MESSAGE {


    GUI_MENU_TITLE_BANKER("gui.menu.title_banker","&e&lBanker"),
    GUI_MENU_TITLE_WITHDRAW("gui.menu.title_withdraw","&cWITHDRAW"),
    GUI_MENU_TITLE_DEPOSIT("gui.menu.title_deposit","&cDEPOSIT"),
    GUI_ITEM_TITLE_INFO("gui.item.title_info","&aINFORMATION"),
    GUI_ITEM_TITLE_DEPOSIT("gui.item.title_deposit","&aDEPOSIT"),
    GUI_ITEM_TITLE_WITHDRAW("gui.item.title_withdraw","&cWITHDRAW"),
    GUI_ITEM_TITLE_LOG("gui.item.title_log","Last Transactions"),

    GUI_ITEM_DEPOSIT_TITLE_FULL("gui.menu.deposit.title_full","Deposit all your money!"),
    GUI_ITEM_DEPOSIT_TITLE_HALF("gui.menu.deposit.title_half","Deposit half of your money!"),
    GUI_ITEM_DEPOSIT_TITLE_CUSTOM("gui.menu.deposit.title_custom","Deposit a custom amount of your money!"),
    GUI_ITEM_WITHDRAW_TITLE_FULL("gui.menu.withdraw.title_full","Withdraw all your money!"),
    GUI_ITEM_WITHDRAW_TITLE_HALF("gui.menu.withdraw.title_half","Withdraw half of your money!"),
    GUI_ITEM_WITHDRAW_TITLE_CUSTOM("gui.menu.withdraw.title_custom","Withdraw a custom amount of your money!"),
    WITHDRAW_CHAT_MESSAGE("chat.withdraw","You have withdraw {amount}$."),
    DEPOSIT_CHAT_MESSAGE("chat.deposit","You deposited {amount}$."),
    GUI_ITEM_CLOSE("gui.menu.close","Close this menu"),
    GUI_ITEM_LORE_TRANSACTION_FORMAT("transaction_format","You have {type} {amount}$      ago {ago}"),
    TYPE_DEPOSIT("deposit","deposited"),
    TYPE_WITHDRAW("withdraw","withdraw"),
    INVALID_NUMBER_FORMAT("invalid_amount","Invalid amount"),
    INSERT_AMOUNT_TITLE("title_amount","Insert a number!"),
    NOT_ENOUGH_MONEY_TO_DEPOSIT("not_enough.deposit","You don`t have {amount} to deposit"),
    NOT_ENOUGH_MONEY_TO_WITHDRAW("not_enough.withdraw","You don`t have {amount} to withdraw"),
    MONEY_LOSE_DEATH("death","You died and lose {amount}$");




    String path,Default;

    MESSAGE(String _path, String _default) {
        path = _path;
        Default = _default;
    }
}

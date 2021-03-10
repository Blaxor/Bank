package ro.deiutzblaxo.bank.config;

public enum MESSAGELIST {

    GUI_ITEM_WITHDRAW_LORE_FULL("gui.menu.withdraw.lore_full",new String[]{"Click to withdraw your money from the bank ","You have in bank {amount}"}),
    GUI_ITEM_WITHDRAW_LORE_HALF("gui.menu.withdraw.lore_half",new String[]{"Click to withdraw half money from the bank! ","You have in bank {amount}"}),
    GUI_ITEM_WITHDRAW_LORE_CUSTOM("gui.menu.withdraw.lore_custom",new String[]{"Click to withdraw a custom amount of money ","You have in bank {amount}"}),
    GUI_ITEM_DEPOSIT_LORE_FULL("gui.menu.deposit.lore_full",new String[]{"Click to deposit your money from the bank! ","You have in bank {amount}"}),
    GUI_ITEM_DEPOSIT_LORE_HALF("gui.menu.deposit.lore_half",new String[]{"Click to deposit half money from the bank! ","You have in bank {amount}"}),
    GUI_ITEM_DEPOSIT_LORE_CUSTOM("gui.menu.deposit.lore_custom",new String[]{"Click to deposit a custom amount of money ","You have in bank {amount}"}),
    GUI_ITEM_LORE_DEPOSIT("gui.item.lore_deposit",new String[]{"Click to open deposit options!"}),
    GUI_ITEM_LORE_WITHDRAW("gui.item.lore_withdraw",new String[]{"Click to open withdraw options!"}),
    GUI_ITEM_LORE_INFO("gui.item.lore_info",new String[]{"&fThis is the banker.","&fHe can help you to store your money","&eBut you need to withdraw money to use it!"});

    String path;
    String[] _default;
    MESSAGELIST(String path, String[] _default) {
        this.path = path;
        this._default = _default;
    }
}

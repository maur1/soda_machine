public enum SodaMachineState {
    DefaultScreen {
        @Override
        public String info(Wallet wallet, Inventory inventory) {
            String message = "\n\nAvailable commands:"
            + "\n INSERT - Money put into money slot"
            + "\n ORDER- Order from machines buttons"
            + "\n SMS_ORDER - Order sent by sms"
            + "\n RECALL - gives money back"
            + "\n-------"
            + "\nInserted credit: " + wallet.getCredit()
            + "\n-------\n\n";
            return message;
        }

        @Override
        public SodaMachineState process(String input, Wallet wallet, Inventory inventory) {
            switch (Command.valueOf(input)) {
                case INSERT:
                    return InsertMoney;
                case ORDER: {
                    return OrderReceived;
                }
                case SMS_ORDER: {
                    return SMSOrderReceived;
                }
                case RECALL: {
                    System.out.println("Dispensing credit: "+ wallet.getCredit());
                    wallet.returnMoney();
                    return DefaultScreen;
                }
            }
            return null;
        }

    },
    OrderReceived {
        @Override
        public String info(Wallet wallet, Inventory inventory) {
            return (inventory.getInventoryOverview() +
                    "\n Insert one of : \n"
                    + Soda.COKE + "\n"
                    + Soda.FANTA + "\n"
                    + Soda.SPRITE);
        }

        @Override
        public SodaMachineState process(String input, Wallet wallet, Inventory inventory) {
            // Improvement potential - dry violation, ugly try catch logic,
            // TODO: money should not be dispensed if inventory is empty
            Soda soda = Soda.valueOf(input);

            try {
                wallet.creditCheck(soda);
                try {
                    inventory.handleInventory(soda);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Dispensing credit: "+ wallet.getCredit());
                    wallet.returnMoney();
                    return DefaultScreen;
                }
                wallet.subtractCredit(soda.getCost());
                System.out.println("Dispensing " + soda);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            return DefaultScreen;
        }

    },
    SMSOrderReceived {
        @Override
        public String info(Wallet wallet, Inventory inventory) {
            return (inventory.getInventoryOverview() +
                    "\n Insert one of : \n"
                    + Soda.COKE + "\n"
                    + Soda.FANTA + "\n"
                    + Soda.SPRITE);
        }

        @Override
        public SodaMachineState process(String input, Wallet wallet, Inventory inventory) {
            Soda soda = Soda.valueOf(input);

            try {
                inventory.handleInventory(soda);
                System.out.println("Dispensing " + soda);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            return DefaultScreen;
        }

    },
    InsertMoney {
        @Override
        public String info(Wallet wallet, Inventory inventory) {
            return "INPUT INSERT AMOUNT 5,10,20";
        }


        @Override
        public SodaMachineState process(String input, Wallet wallet, Inventory inventory) {
            // Should validate input
            wallet.addToCredit(input);
            System.out.println("Added " + input + "to wallet, total is now: " + wallet.getCredit());
            return DefaultScreen;
        }

    };

    public abstract String info(Wallet wallet, Inventory inventory);
    public abstract SodaMachineState process(String input, Wallet wallet, Inventory inventory);

};



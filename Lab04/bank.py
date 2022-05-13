class NotEnoughCash(Exception):
    pass


class BankAccount(object):
    def __init__(self, initial_amount=0):
        self.balance = initial_amount

    def withdraw(self, amount):
        if self.balance < amount:
            raise NotEnoughCash('Not enough available to spend {}'.format(amount))
        self.balance -= amount

    def deposit(self, amount):
        self.balance += amount

    def subtract_vat_eight(self):
        if self.balance <= 100:
            raise NotEnoughCash('Not enough available to calculate vat 8%')
        self.balance -= (self.balance*0.08)

    def subtract_vat_twenty_three(self):
        if self.balance <= 100:
            raise NotEnoughCash('Not enough available to calculate vat 23%')
        self.balance -= (self.balance * 0.23)

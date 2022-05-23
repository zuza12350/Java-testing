import pytest

from bank import BankAccount, NotEnoughCash


class TestRichPersonAccount:

    def test_initial_amount_of_rich_account(self):
        rich = BankAccount(10000)
        assert rich.balance == 10000

    def test_withdraw_rich_account(self):
        rich = BankAccount(10000)
        rich.withdraw(1000)
        assert rich.balance == 9000

    def test_subtract_vat_twenty_three_rich_account(self):
        rich = BankAccount(10000)
        rich.subtract_vat_twenty_three()
        assert rich.balance == 7700

    def test_subtract_vat_eight_rich_account(self):
        rich = BankAccount(10000)
        rich.subtract_vat_eight()
        assert rich.balance == 9200


class TestPoorPersonAccount:

    def test_initial_amount_of_poor_account(self):
        poor = BankAccount(100)
        assert poor.balance == 100

    def test_poor_account_deposit_200(self):
        poor = BankAccount(100)
        poor.deposit(200)
        assert poor.balance == 300

    def test_withdraw_poor_account(self):
        poor = BankAccount(100)
        poor.withdraw(100)
        assert poor.balance == 0

    def test_subtract_vat_eight_for_poor_account(self):
        poor = BankAccount(100)
        with pytest.raises(NotEnoughCash):
            poor.subtract_vat_eight()


class TestEmptyAccount:

    def test_default_initial_amount(self):
        empty = BankAccount()
        assert empty.balance == 0

    def test_empty_account_deposit_600(self):
        empty = BankAccount()
        empty.deposit(600)
        assert empty.balance == 600

    def test_withdraw_empty_account(self):
        empty = BankAccount()
        with pytest.raises(NotEnoughCash):
            empty.withdraw(100)

    def test_subtract_vat_eight_for_empty_account(self):
        empty = BankAccount()
        with pytest.raises(NotEnoughCash):
            empty.subtract_vat_eight()

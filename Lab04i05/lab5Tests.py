import json

import pytest
from bank import BankAccount, NotEnoughCash


def read_file(string, kind):
    f = open(string + '.json')
    data = json.load(f)
    data1 = data[kind]
    f.close()
    return data1


class TestRichPersonAccount:
    @classmethod
    def setup_class(cls):

        print('\n----setup tests----')

    @classmethod
    def teardown_class(cls):
        print('\n----teardown tests----')

    @pytest.fixture
    def rich_account(self):
        return BankAccount(10000)

    data = read_file("test_data", "data_rich")

    def test_initial_amount_of_rich_account(self, rich_account):
        assert rich_account.balance == 10000

    @pytest.mark.parametrize("withdraw,expected", [
        (50, 9950),
        (2, 9998),
    ])
    def test_withdraw_transactions(self, rich_account, withdraw, expected):
        rich_account.withdraw(withdraw)
        assert rich_account.balance == expected

    @pytest.mark.parametrize("deposit,expected", [
        (50, 10050),
        (2, 10002),
    ])
    def test_deposit_transactions(self, rich_account, deposit, expected):
        rich_account.deposit(deposit)
        assert rich_account.balance == expected

    @pytest.mark.parametrize("deposit,withdraw,expected", data)
    def test_both_transactions_for_rich_person(self, rich_account, deposit, withdraw, expected):
        rich_account.deposit(deposit)
        rich_account.withdraw(withdraw)
        assert rich_account.balance == expected

    def test_subtract_vat_eight_rich_account(self,rich_account):
        rich_account.subtract_vat_eight()
        assert rich_account.balance == 9200


class TestPoorPersonAccount:
    @classmethod
    def setup_class(cls):
        print('\n----setup tests----')

    @classmethod
    def teardown_class(cls):
        print('\n----teardown tests----')

    @pytest.fixture
    def poor_account(self):
        return BankAccount(100)

    data = read_file("test_data", "data_poor")

    def test_initial_amount_of_poor_account(self, poor_account):
        assert poor_account.balance == 100

    @pytest.mark.parametrize("withdraw,expected", [
        (90, 10),
        (10, 90),
    ])
    def test_withdraw_transactions(self, poor_account, withdraw, expected):
        poor_account.withdraw(withdraw)
        assert poor_account.balance == expected

    @pytest.mark.parametrize("deposit,expected", [
        (50, 150),
        (2, 102),
    ])
    def test_deposit_transactions(self, poor_account, deposit, expected):
        poor_account.deposit(deposit)
        assert poor_account.balance == expected

    @pytest.mark.parametrize("deposit,withdraw,expected", data)
    def test_both_transactions_for_poor_person(self, poor_account, deposit, withdraw, expected):
        poor_account.deposit(deposit)
        poor_account.withdraw(withdraw)
        assert poor_account.balance == expected

    def test_subtract_vat_eight_for_poor_account(self,poor_account):
        with pytest.raises(NotEnoughCash):
            poor_account.subtract_vat_eight()


class TestEmptyAccount:
    @classmethod
    def setup_class(cls):
        print('\n----setup tests----')

    @classmethod
    def teardown_class(cls):
        print('\n----teardown tests----')

    @pytest.fixture
    def empty_account(self):
        return BankAccount()

    data = read_file("test_data", "data_empty")

    def test_initial_amount_of_empty_account(self, empty_account):
        assert empty_account.balance == 0

    @pytest.mark.parametrize("deposit,expected", [
        (50, 50),
        (2, 2),
    ])
    def test_deposit_transactions(self, empty_account, deposit, expected):
        empty_account.deposit(deposit)
        assert empty_account.balance == expected

    @pytest.mark.parametrize("deposit,withdraw,expected", data)
    def test_both_transactions_for_poor_person(self, empty_account, deposit, withdraw, expected):
        empty_account.deposit(deposit)
        empty_account.withdraw(withdraw)
        assert empty_account.balance == expected

    def test_withdraw_empty_account(self, empty_account):
        with pytest.raises(NotEnoughCash):
            empty_account.withdraw(100)

    def test_subtract_vat_eight_for_empty_account(self,empty_account):
        with pytest.raises(NotEnoughCash):
            empty_account.subtract_vat_eight()

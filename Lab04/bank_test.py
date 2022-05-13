import pytest
from bank import BankAccount, NotEnoughCash


@pytest.fixture
def empty_account():
    return BankAccount()


@pytest.fixture
def rich_account():
    return BankAccount(10000)


@pytest.fixture
def poor_account():
    return BankAccount(100)


def test_default_initial_amount(empty_account):
    assert empty_account.balance == 0


def test_initial_amount_of_poor_account(poor_account):
    assert poor_account.balance == 100


def test_initial_amount_of_rich_account(rich_account):
    assert rich_account.balance == 10000


def test_withdraw_rich_account(rich_account):
    rich_account.withdraw(1000)
    assert rich_account.balance == 9000


def test_subtract_vat_twenty_three_rich_account(rich_account):
    rich_account.subtract_vat_twenty_three()
    assert rich_account.balance == 7700


def test_subtract_vat_eight_rich_account(rich_account):
    rich_account.subtract_vat_eight()
    assert rich_account.balance == 9200


def test_empty_account_withdraw_too_much(empty_account):
    with pytest.raises(NotEnoughCash):
        empty_account.withdraw(120)


def test_poor_account_deposit_200(poor_account):
    poor_account.deposit(200)
    assert poor_account.balance == 300


@pytest.mark.parametrize("deposit,withdraw,expected", [
    (100, 50, 50),
    (200, 2, 198),
])
def test_transactions(empty_account, deposit, withdraw, expected):
    empty_account.deposit(deposit)
    empty_account.withdraw(withdraw)
    assert empty_account.balance == expected


@pytest.mark.parametrize("deposit,withdraw,expected", [
    (100, 50, 10050),
    (200, 2, 10198),
])
def test_transactions(rich_account, deposit, withdraw, expected):
    rich_account.deposit(deposit)
    rich_account.withdraw(withdraw)
    assert rich_account.balance == expected


@pytest.mark.parametrize("deposit,withdraw,expected", [
    (100, 50, 150),
    (200, 100, 200),
])
def test_transactions(poor_account, deposit, withdraw, expected):
    poor_account.deposit(deposit)
    poor_account.withdraw(withdraw)
    assert poor_account.balance == expected


def test_subtract_vat_eight_for_poor_account(poor_account):
    with pytest.raises(NotEnoughCash):
        poor_account.subtract_vat_eight()


def test_subtract_vat_twenty_three_for_poor_account(poor_account):
    with pytest.raises(NotEnoughCash):
        poor_account.subtract_vat_twenty_three()

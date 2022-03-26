# Test scenarios

Two websites were used for testing
[Amazon registration form](https://www.amazon.pl/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.pl%2F%3F_encoding%3DUTF8%26ref_%3Dnav_custrec_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=plflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&)
and
[BMI Calculator](https://calc.pl/zdrowie/bmi)

## Test scenarios for registering on Amazon using FirefoxDriver

### `validUserCredentialsShouldPassTheForm`
The method tests whether the form can be validated after all inputs have been filled in. After going to the page after registration the test should intercept the message asking to enter the code sent by the page to the email.
Screen after successful registration

![img_10.png](screenshots/img_10.png)

![img_21.png](screenshots/img_21.png)
### `badlyRepeatedPasswordShouldNotLetTheFormPass`
The method tests whether you can proceed to registration confirmation with a code after entering a badly repeated password

![img_11.png](screenshots/img_11.png)

![img_22.png](screenshots/img_22.png)
### `invalidEmailCredentialsShouldNotLetTheFormPass`
The method tests whether you can proceed to confirm the registration with a code after entering an invalid one. That is, whether the site is able to validate the email.

![img_13.png](screenshots/img_13.png)

![img_23.png](screenshots/img_23.png)
### `firstNameFieldShouldNotBeEmpty`
I am testing a method to see if you can approve a registration form without filling in the first name field.

![img_15.png](screenshots/img_15.png)

![img_24.png](screenshots/img_24.png)
### `passwordFieldShouldNotBeEmpty`
I'm testing a method to see if you can approve a registration form without filling out the password field.

![img_17.png](screenshots/img_17.png)

![img_25.png](screenshots/img_25.png)
### `siteShouldDisplayAlertWhenEmailIsUsed`
The method tests if the form registers the user if the email is already in use

![img_19.png](screenshots/img_19.png)

![img_20.png](screenshots/img_20.png) 
## Test scenarios for calculating BMI on calc.pl using ChromeDriver

### `allFieldsFilledShouldDisplayBMI`
The method checks whether the result is displayed after all fields have been filled in

![img.png](screenshots/img.png)

![img_26.png](screenshots/img_26.png)
### `bmiWithProvidedDataShouldDisplay1651ForWoman`
The method checks if the program calculates correct BMI for woman, i.e. if it is 16.51, after entering the weight field to 50 and the height field to 174

![img_2.png](screenshots/img_2.png)

![img_27.png](screenshots/img_27.png)
### `bmiWithProvidedDataShouldDisplay661ForMen`
The method checks if the program calculates correct BMI for man, i.e. if it is 6.61, after entering the weight field to 20 and the height field to 174

![img_4.png](screenshots/img_4.png)

![img_29.png](screenshots/img_29.png)
### `accordingToTheTableNextToItBMIWithDataShouldDisplayHungerButItIsNot`
The method checks if the name pointing to the given indicator in the table provided next to the form is displayed correctly

![img_7.png](screenshots/img_7.png)

![img_28.png](screenshots/img_28.png)
### All testing classes includes methods called setUp, clearUp and closeBrowser
They are responsible for setting the driver and logger, deleting the data entered in the input fields and closing the browser after the tests have been run.


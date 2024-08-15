@tag
Feature: Error Validation
I want to use this template for my feature file

@tag2
Scenario Outline:Title of scenario outline
Given I landed on Ecommerce Page
When Logged in with username <name> and password<password>
Then "Incorrect email or password." message is displayed
Examples:
| name                            | password           | productName
| vdivyaja.s@gmail.com            | vidisha13         | ADIDAS ORIGINAL


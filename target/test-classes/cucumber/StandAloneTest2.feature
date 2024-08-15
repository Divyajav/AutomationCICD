@tag
Feature: Purchase the order from E commerce Web site
I want to use this template for my feature file


Background:
Given I landed on Ecommerce Page

@tag2
Scenario Outline: Positive Test of Submitting the order
  Given Logged in with Username <name> and Password <password>
  When I add product <productName> to cart
  And Checkout <productName> and submit the order
  Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
  
Examples:
| name                            | password           | productName
| vdivyaja.s@gmail.com            | vidisha@13         | ADIDAS ORIGINAL
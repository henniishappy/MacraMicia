// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
Cypress.Commands.add('login_as_admin', () => {
  cy.visit(Cypress.env('url_login'))
  cy.get('[data-cy=loginInputUsername]').type(Cypress.env('username_admin'))
  cy.get('[data-cy=loginInputPassword]').type(Cypress.env('password_admin')+"{enter}")
    .url().should('eq', Cypress.env('baseUrl'))
})

Cypress.Commands.add('signup_as_user', () => {
  cy.visit(Cypress.env('url_signup'))
  cy.get('[data-cy=registrationInputFirstName]').should('be.empty').type(Cypress.env('firstname_user'))
  cy.get('[data-cy=registrationInputLastName]').should('be.empty').type(Cypress.env('lastname_user'))
  cy.get('[data-cy=registrationInputEmail]').should('be.empty').type(Cypress.env('email_user'))
  cy.get('[data-cy=registrationInputUsername]').should('be.empty').type(Cypress.env('username_user'))
  cy.get('[data-cy=registrationInputPassword]').should('be.empty').type(Cypress.env('password_user')+"{enter}")
    .url().should('eq', Cypress.env('url_login'))
})

Cypress.Commands.add('login_as_user', () => {
  cy.visit(Cypress.env('url_login'))
  cy.get('[data-cy=loginInputUsername]').type(Cypress.env('username_user'))
  cy.get('[data-cy=loginInputPassword]').type(Cypress.env('password_user')+"{enter}")
    .url().should('eq', Cypress.env('baseUrl'))
})

Cypress.Commands.add('signup_login_as_user', () => {
  cy.signup_as_user()
  cy.login_as_user()
})

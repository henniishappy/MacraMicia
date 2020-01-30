describe('The Sign up form', () => {
    beforeEach(() => {
        cy.visit(Cypress.env('url_signup'))
    })

    it('greets with Sign Up', () => {
        cy.contains('Sign Up')
    })

    it('displays the labels in the form', () => {
        cy.get('[data-cy=registrationLabelFirstName]').contains('First name')
        cy.get('[data-cy=registrationLabelLastName]').contains('Last name')
        cy.get('[data-cy=registrationLabelEmail]').contains('Email')
        cy.get('[data-cy=registrationLabelUsername]').contains('Username')
        cy.get('[data-cy=registrationLabelPassword]').contains('Password')
    })

    it('requires username', () => {
        cy.get('[data-cy=registrationInputFirstName]').should('be.empty').type(Cypress.env('firstname_user'))
        cy.get('[data-cy=registrationInputLastName]').should('be.empty').type(Cypress.env('lastname_user'))
        cy.get('[data-cy=registrationInputEmail]').should('be.empty').type(Cypress.env('email_user'))
        cy.get('[data-cy=registrationInputUsername]').should('be.empty')
        cy.get('[data-cy=signUpButton]').click()
          .url().should('eq', Cypress.env('url_signup'))
    })

    it('requires password', () => {
      cy.get('[data-cy=registrationInputFirstName]').should('be.empty').type(Cypress.env('firstname_user'))
      cy.get('[data-cy=registrationInputLastName]').should('be.empty').type(Cypress.env('lastname_user'))
      cy.get('[data-cy=registrationInputEmail]').should('be.empty').type(Cypress.env('email_user'))
      cy.get('[data-cy=registrationInputUsername]').should('be.empty').type(Cypress.env('username_user')+"{enter}")
        .url().should('eq', Cypress.env('url_signup'))
    })

    it('navigates to ' + Cypress.env('url_login') + " on successful sign up", () => {
      cy.get('[data-cy=registrationInputFirstName]').should('be.empty').type(Cypress.env('firstname_user'))
      cy.get('[data-cy=registrationInputLastName]').should('be.empty').type(Cypress.env('lastname_user'))
      cy.get('[data-cy=registrationInputEmail]').should('be.empty').type(Cypress.env('email_user'))
      cy.get('[data-cy=registrationInputUsername]').should('be.empty').type(Cypress.env('username_user'))
      cy.get('[data-cy=registrationInputPassword]').should('be.empty').type(Cypress.env('password_user')+"{enter}")
        .url().should('eq', Cypress.env('url_login'))
    })
})

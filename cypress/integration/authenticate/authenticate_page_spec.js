describe('The login page', () => {
    beforeEach(() => {
        cy.visit(Cypress.env('url_login'))
    })

    it('greets with Login', () => {
        cy.contains('Login')
    })

    it('displays labels', () => {
        cy.get('[data-cy=loginLabelUsername]').contains('Username')
        cy.get('[data-cy=loginLabelPassword]').contains('Password')
    })

    it('requires username', () => {
        cy.get('[data-cy=loginInputUsername]').should('be.empty')
        cy.get('[data-cy=loginButton]').click()
          .url().should('eq', Cypress.env('url_login'))
    })

    it('requires password', () => {
        cy.get('[data-cy=loginInputUsername]').type('user{enter}')
          .url().should('eq', Cypress.env('url_login'))
    })

    it('requires valid username and password', () => {
        cy.get('[data-cy=loginInputUsername]').type('user')
        cy.get('[data-cy=loginInputPassword]').type('invalid{enter}')
        cy.contains('Bad credentials')
    })

    it('navigates to / on successful login', () => {
        cy.get('[data-cy=loginInputUsername]').type('user')
        cy.get('[data-cy=loginInputPassword]').type('pass{enter}')
          .url().should('eq', Cypress.env('baseUrl'))
    })
})

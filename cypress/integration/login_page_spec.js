describe('The login page', function() {
    it('successfully loads', function() {
        cy.visit('/login')
    })

    it('displays the website name', function() {
        cy.contains('MacraMicia')
    })

    it('displays the links to other pages', function() {
        cy.get('[data-cy=homeNav]')
        cy.get('[data-cy=coursesNav]')
        cy.get('[data-cy=aboutNav]')
        cy.get('[data-cy=loginNav]')
        cy.get('[data-cy=signUpNav]')
    })

    it('displays the labels in the form', function () {
        cy.get('[data-cy=loginLabelUsername]').contains('Username')
        cy.get('[data-cy=loginLabelPassword]').contains('Password')
    })

    it('displays the inputs in the form', function () {
        cy.get('[data-cy=loginInputUsername]').should('be.empty')
        cy.get('[data-cy=loginInputPassword]').should('be.empty')
    })

    it('displays the button in the form', function () {
        cy.get('[data-cy=loginButton]').should('be.enabled').click()
            .url().should('include', 'login')
    })

})
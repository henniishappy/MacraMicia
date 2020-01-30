describe('The login page', function() {
    beforeEach(() => {
        cy.visit('/user/login')
    })

    it('greets with Login', function() {
        cy.contains('Login')
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

    it('successfully logs in as admin', function () {
        cy.login_as_admin()
          .then((resp) => {
            return resp.body;
          })
        cy.contains('Welcome to our Website!')
    })
})

//cy.request('POST', '/test/seed/user', { name: 'Jane' }).its('body').as('currentUser')

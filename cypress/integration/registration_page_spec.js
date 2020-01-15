describe('The Sign up form', function () {
    it('successfully loads', function () {
        cy.visit('user/registration')
    })

    it('displays the website name', function() {
        cy.contains('Macra Micia')
    })

    it('displays the links to other pages', function() {
        cy.get('[data-cy=homeNav]')
        cy.get('[data-cy=coursesNav]')
        cy.get('[data-cy=aboutNav]')
        cy.get('[data-cy=loginNav]')
        cy.get('[data-cy=signUpNav]')
    })

    it('displays the labels in the form', function () {
        cy.get('[data-cy=registrationLabelFirstName]').contains('First name')
        cy.get('[data-cy=registrationLabelLastName]').contains('Last name')
        cy.get('[data-cy=registrationLabelEmail]').contains('Email')
        cy.get('[data-cy=registrationLabelUsername]').contains('Username')
        cy.get('[data-cy=registrationLabelPassword]').contains('Password')
    })

    it('displays the inputs in the form', function () {
        cy.get('[data-cy=registrationInputFirstName]').should('be.empty')
        cy.get('[data-cy=registrationInputLastName]').should('be.empty')
        cy.get('[data-cy=registrationInputEmail]').should('be.empty')
        cy.get('[data-cy=registrationInputUsername]').should('be.empty')
        cy.get('[data-cy=registrationInputPassword]').should('be.empty')
    })

    it('displays the button in the form', function () {
        cy.get('[data-cy=signUpButton]').should('be.enabled').click()
            .url().should('include', 'registration')
    })

    it('redirects to /user/login after filling out the form and clicking the Sign Up button', function () {
        cy.get('[data-cy=registrationInputFirstName]').type('exampleFirstName')
        cy.get('[data-cy=registrationInputLastName]').type('exampleLastName')
        cy.get('[data-cy=registrationInputEmail]').type('exampleEmail')
        cy.get('[data-cy=registrationInputUsername]').type('exampleUsername')
        cy.get('[data-cy=registrationInputPassword]').type('examplePassword')
        cy.get('[data-cy=signUpButton]').should('be.enabled').click()
            .url().should('include', '/registration/create')
    })
})
describe('The Sign up form', function () {

    it('should show registration link in navbar', function () {
        cy.contains('Sign up')
    })

    it('should go to /registration', function () {
        cy.visit('/registration')
    })
})
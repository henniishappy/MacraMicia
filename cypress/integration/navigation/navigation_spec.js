describe('Test the navigation menu', () => {
  it('displays the links to other pages',() => {
    cy.visit('localhost:8080/')
    cy.get('[data-cy=homeNav]')
    .should('be.visible')
    .should('have.attr', 'href', '/')

    cy.get('[data-cy=coursesNav]')
    .should('be.visible')
    .should('have.attr', 'href', '/courses/all')

    cy.get('[data-cy=aboutNav]')
    .should('be.visible')
    .should('have.attr', 'href', '/aboutus')

    cy.get('[data-cy=loginNav]')
    .should('be.visible')
    .should('have.attr', 'href', '/user/login')

    cy.get('[data-cy=signUpNav]')
    .should('be.visible')
    .should('have.attr', 'href', '/user/registration')
  })
})

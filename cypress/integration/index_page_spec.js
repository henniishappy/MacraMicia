describe('The index page', function() {
  it('successfully loads', function() {
    cy.visit('/')
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

  it('displays the welcoming message', function() {
    cy.contains('Welcome to our Website!')
  })

  it('lets You visit the Index page', function() {
    cy.get('[data-cy=homeNav]').click()
  })

  it('lets You visit the Courses page', function() {
    cy.get('[data-cy=coursesNav]').click()
    cy.url()
        .should('include', '/courses/all')
  })

  it('lets You visit the AboutUs page', function() {
    cy.get('[data-cy=aboutNav]').click()
    cy.url()
      .should('include', '/aboutus')
  })

  it('lets You visit the Login page', function() {
    cy.get('[data-cy=loginNav]').click()
    cy.url()
      .should('include', '/login')
  })

  it('lets You visit the SignUp page', function() {
    cy.get('[data-cy=signUpNav]').click()
    cy.url()
        .should('include', '/registration')
  })

})

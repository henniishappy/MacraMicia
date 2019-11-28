describe('The index page', function() {
  it('successfully loads', function() {
    cy.visit('/')
  })

  it('displays the website name', function() {
    cy.contains('MacraMicia')
  })

  it('displays the links to other pages', function() {
    cy.contains('Home')
    cy.contains('About Us')
  })

  it('displays the welcoming message', function() {
    cy.contains('Welcome to our Website!')
  })

  it('lets You visit the AboutUs page', function() {
    cy.contains('About Us').click()
    cy.url()
      .should('include', '/aboutUs')
  })

  it('lets You visit the Courses page', function() {
    cy.contains('Courses').click()
    cy.url()
      .should('include', '/courses/all')
  })

    it('lets You visit the Create Course page', function() {
    cy.contains('Create Course').click()
    cy.url()
      .should('include', '/courses/add')
  })

})

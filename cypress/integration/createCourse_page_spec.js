describe('The create courses page', function() {
  it('successfully loads', function() {
    cy.visit('/courses/add')
  })

  it('displays the website name', function() {
    cy.contains('MacraMicia')
  })

  it('displays the links to other pages', function() {
    cy.contains('Home')
    cy.contains('About Us')
    cy.contains('Create Course')
    cy.contains('Courses')
  })

  it('displays the subpage name', function() {
    cy.contains('Create a new course')
  })

  it('should load index.html when clicking the Home button', function() {
    cy.contains('Home').click()
    cy.url()
      .should('include', '/')
  })

    it('lets You visit the About Us page', function() {
    cy.contains('About Us').click()
    cy.url()
      .should('include', '/aboutUs')
  })

    it('lets You visit the Create Course page', function() {
    cy.contains('Courses').click()
    cy.url()
      .should('include', '/courses/all')
  })

})
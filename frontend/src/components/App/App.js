import './App.css';
import {Component} from 'react';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit"
import Books from '../Books/BookList/books';
import LibraryService from '../../repository/libraryRepository';
import Authors from "../Authors/AuthorList/authors";
import AuthorAdd from "../Authors/AuthorAdd/authorAdd";
import Countries from "../Countries/CountryList/countries";
import CountryAdd from "../Countries/CountryAdd/countryAdd";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      books: [],
      authors: [],
      countries: [],
      categories: [],
      selectedBook: {}
    }
  }

  render() {
    return (
      <Router>
       <Header/>
        <main>
          <div className="container">
              <Route path={"/countries/add"} exact render ={() =>
              <CountryAdd onAddCountry={this.addCountry}/>}/>
              <Route path={"/countries"} exact render = {() =>
              <Countries countries = {this.state.countries}
                         onDeleteCountry={this.deleteCountry}/>}/>
              <Route path={"/authors/add"} exact render = {() =>
              <AuthorAdd  countries={this.state.countries}
                          onAddAuthor={this.addAuthor}/>}/>
              <Route path={"/authors"} exact render = {() =>
              <Authors authors = {this.state.authors}
                       onDeleteAuthor={this.deleteAuthor}/>}/>
              <Route path={"/books/add"} exact render={() =>
                  <BookAdd
                            categories={this.state.categories}
                            authors={this.state.authors}
                            onAddBook={this.addBook}/>}/>
             <Route path={"/books/edit/:id"} exact render = {() =>
             <BookEdit  categories={this.state.categories}
                        authors={this.state.authors}
                        book={this.state.selectedBook}
                        onEditBook={this.editBook}/>}/>
            <Route path={["/", "/books"]} exact render={() =>
              <Books books={this.state.books}
                     onDeleteBook={this.deleteBook}
                     onMarkAsTaken={this.markBookAsTaken}
                     onselectBook={this.getBook}/>
                    }/>
            <Redirect to={"/"}/>
          </div>
        </main>
      </Router>
    );
  }

  componentDidMount() {
     this.loadCategories();
     this.loadCountries();
     this.loadAuthors();
     this.loadBooks();
  }

  loadBooks = () => {
    LibraryService.fetchBooks()
        .then((data)=> {
          this.setState({
            books: data.data
          })
        });
  }
    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data)=> {
                this.setState( {
                    authors: data.data
                })
            });
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data)=> {
                this.setState({
                    countries: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });

    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    markBookAsTaken = (id) => {
        LibraryService.markBookAsTaken(id)
            .then(() =>{
                this.loadBooks();
            });
    }

    addAuthor = (name, surname, country) => {
      LibraryService.addAuthor(name,surname,country)
          .then(()=> {
              this.loadAuthors();
          })
    }

    deleteAuthor = (id) => {
      LibraryService.deleteAuthor(id)
          .then(() => {
              this.loadAuthors();
          })
    }

    addCountry = (name, continent) => {
      LibraryService.addCountry(name, continent)
          .then(() => {
              this.loadCountries();
          })
    }

    deleteCountry = (id) => {
      LibraryService.deleteCountry(id)
          .then(()=> {
              this.loadCountries();
          })
    }



}

export default App;

import React from 'react';
import {useHistory} from 'react-router-dom';

const BookEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
            name: "",
            category: 0,
            author: 0,
            availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== 0 ? formData.category : props.book.category;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id, name, category, author, availableCopies);
        history.push("/books");

    }

    return (
        <div className="row mt-4">
            <div className={"col-md-4"}>
                <form onSubmit={onFormSubmit}>
                    <div className={"form-group"}>
                        <label htmlFor="name">Book Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className={"form-group"}>
                        <label htmlFor="category">Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) =>
                            {
                                if(props.book.category !== undefined &&
                                    props.book.category === term)
                                return <option selected={props.book.category} value={term}>{term}</option>
                                else return <option value={term}>
                                    {term}
                                </option>
                            })}
                        </select>
                    </div>
                    <div className={"form-group"}>
                        <label htmlFor="author">Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if(props.book.author !== undefined &&
                                        props.book.author.id===term.id)
                                    return <option selected={props.book.author.id} value={term.id}>{term.name} {term.surname}</option>
                               else return <option value={term.id}>{term.name} {term.surname}</option>
                            })}
                        </select>
                    </div>
                    <div className={"form-group"}>
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               min="1"
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="text-center">
                        <button id="submit" type="submit" className="btn btn-block btn-info">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    )

}
export default BookEdit;
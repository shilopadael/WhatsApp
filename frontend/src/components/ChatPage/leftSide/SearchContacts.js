import { useRef } from 'react'

function SearchContacts(props) {

    const { contacts , setContactToShow} = props
    const searchRef = useRef("");

    function modiftyShowContact(e) {
        const value = e.target.value;
        setContactToShow(contacts.filter(contact => contact.displayName.includes(value)));
    }

    function handleKeyPress(e) {
        if (e.key === 'Enter') {
            e.preventDefault();
          }
    }

    return (
        <div>
            <nav className="navbar navbar-light">
                <div className="container-fluid contact-search-bar">
                    <form className="d-flex">
                        <input className="form-control border-none"
                            type="search"
                            placeholder="Search contact"
                            aria-label="Search"
                            ref={searchRef}
                            onChange={modiftyShowContact}
                            onKeyPress={handleKeyPress}
                        ></input>
                    </form>
                </div>
            </nav>
        </div>
    )
}


export default SearchContacts;
import { useState } from "react";
import defaultProfile from '../../../assets/registerImg/profile.png';

const data = {
  1: "dragonball is the best",
  2: "dont eat the mellon!",
  3: "this is a status",
  4: "normal status",
  5: "stupid status"
}

function AddContact(props) {
  const { contacts, setContacts, contactToShow, setContactToShow } = props;

  const [newItem, setNewItem] = useState("");
  const [showModal, setShowModal] = useState(false);

  function addContact(name) {

    var random = Math.floor(Math.random() * (5 - 1 + 1)) + 1
    var newId = contacts.length + 1;
    const newContact = {
      id: newId,
      name: name,
      image: defaultProfile,
      lastMessageTime: "",
      lastMessageDate: "",
      unRead: 0,
      lastMessage: "",
      messages: [],
      status: data[random],
    };

    setContacts([...contacts, newContact]);
    setContactToShow([...contactToShow, newContact])

  };

  function handleSubmit(e) {
    e.preventDefault();
    addContact(newItem);
    setNewItem("");
  }


  function handleIconClick() {
    setShowModal(true);

  }

  function handleAddContact() {
    addContact(newItem);
    setNewItem("");
    setShowModal(false);


  }

  function handleCancel() {
    setShowModal(false);
  }

  function handleKeyDown(event) {
    if (event.key === 'Enter') {
      event.preventDefault();
      addContact(newItem);
      setNewItem("");
      setShowModal(false);

    }; // Prevent the default behavior of the key event
  }

  return (
    <>
      <button
        type="button"
        id="logoutBtn"
        className="btn add-contact-btn"
        onClick={handleIconClick}
        data-bs-toggle="tooltip" // Add the data-bs-toggle attribute for tooltip
        title="Add Contact" // Specify the tooltip text

      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          className="bi bi-person-plus-fill add-contact-img"
          viewBox="0 0 16 16">
          <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
          <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z" />
        </svg>
      </button>

      {showModal && (
        <div className="modal fade show" tabIndex="-1" role="dialog" style={{ display: 'block' }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header bg-dark">
                <h5 className="modal-title logOutModalTitle">Add new Contact</h5>
                {/* <button type="button" className="close" onClick={handleCancel}>
                  <span aria-hidden="true">&times;</span>
                </button> */}

              </div>
              <div className="modal-body danger-btn">
                <form
                  onSubmit={handleAddContact}
                  className="d-flex">
                  <input
                    onChange={(e) => setNewItem(e.target.value)}
                    className="form-control me-2"
                    type="search"
                    placeholder="Add contact"
                    value={newItem}
                    aria-label="Search"
                    onKeyPress={handleKeyDown}></input>

                </form>



              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={handleCancel}>
                  Cancel
                </button>
                <button type="button" className="btn linerLine-color white" onClick={handleAddContact}>
                  Add Contact
                </button>
                <p></p>
              </div>
              {/* <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={handleCancel}>
                  Cancel
                </button>
                <button type="button" className="btn btn-primary" onClick={handleLogout}>
                  Log Out
                </button>
              </div> */}
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default AddContact;

import { useState } from "react";
import post from "../../../services/post-service";

function AddContact(props) {

  const { contacts,
    setContacts,
    contactToShow,
    setContactToShow,
    setAddContact,
    addContact,
    socket } = props;

  const [newItem, setNewItem] = useState("");
  const [showModal, setShowModal] = useState(false);

  function handleIconClick() {
    setShowModal(true);
  }

  const handleAddContact = async () => {
    // trying to create a new contact with the given name
    const user = await post.Contact(newItem);
    if (user !== null) {
      console.log(user);
      let data = {
        id: user.id,
        user: user,
        lastMessage: null,
      }

      setAddContact(addContact ^ true)
      setContacts([...contacts, data]);
      setContactToShow([...contactToShow, data]);
      setNewItem("");
      setShowModal(false);
      socket.emit('adding-contact', { username: user.username });
    } else {
      alert(localStorage.getItem("error"));
      setNewItem("");
    }
  };


  function handleCancel() {
    setShowModal(false);
  }

  function handleKeyDown(event) {
    if (event.key === 'Enter') {
      event.preventDefault();
      handleAddContact();
    };
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
          <path fillRule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z" />
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
                    placeholder="enter contact username"
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

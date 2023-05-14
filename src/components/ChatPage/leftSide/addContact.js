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

  return (
    <>
      <button
        type="button"
        id="addContactBtn"
        className="btn"
        data-bs-toggle="modal"
        data-bs-target="#addContactModal"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          className="bi bi-person-plus-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"
          />
          <path
            fillRule="evenodd"
            d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"
          />
        </svg>
      </button>

      <div className="modal fade" id="addContactModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header bg-primary">
              <h5 className="modal-title" id="exampleModalLabel">
                Add New Contact
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="newItemInput"
                    placeholder="Enter contact name"
                    value={newItem}
                    onChange={(e) => setNewItem(e.target.value)}
                  />
                </div>
                <button
                  type="submit"
                  className="btn btn-primary text-light"
                  disabled={!newItem}
                >
                  Add Contact
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AddContact;

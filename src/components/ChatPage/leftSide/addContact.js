import { useState } from "react";
import defaultProfile from '../../../assets/registerImg/profile.png';

function AddContact(props) {
  const { contacts, setContacts } = props;

  const [newItem, setNewItem] = useState("");


  function addContact(name) {
    var newId = contacts.length + 1;
    const newContact = { id: newId, name: name, image: defaultProfile, lastSeen: "now", unRead: 0, lastMessage: "", messages: [] };
    setContacts([...contacts, newContact]);

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
        class="btn"
        data-bs-toggle="modal"
        data-bs-target="#addContactModal"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="bi bi-person-plus-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"
          />
          <path
            fill-rule="evenodd"
            d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"
          />
        </svg>
      </button>

      <div class="modal fade" id="addContactModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header bg-primary">
              <h5 class="modal-title" id="exampleModalLabel">
                Add New Contact
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form onSubmit={handleSubmit}>
                <div class="mb-3">
                  <input
                    type="text"
                    class="form-control"
                    id="newItemInput"
                    placeholder="Enter contact name"
                    value={newItem}
                    onChange={(e) => setNewItem(e.target.value)}
                  />
                </div>
                <button
                  type="submit"
                  class="btn btn-primary text-light"
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
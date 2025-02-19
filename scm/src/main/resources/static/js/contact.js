
console.log("contacts page")
const viewContactModal = document.getElementById('view_contact_modal');

if (!viewContactModal) {
    console.error("Modal element not found!");
} else {
    console.log("Modal element found, initializing...");
}


// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);
function openContactModal() {
    contactModal.show();
  }
  
  function closeContactModal() {
    contactModal.hide();
  }

  function loadContactData(id){
    console.log("Loading contact data for id: " + id);
    fetch(`http://localhost:8081/api/contacts/${id}`)
    .then(response =>response.json())
    .then(data => {
        console.log(data);  
    })
        .catch(error => {
        console.error(error);
    });
    openContactModal();
  }

console.log('admin.js loaded');
// Image preview
document.querySelector('#image_file_input').addEventListener('change', function() {
  let file = this.files[0];
  let reader = new FileReader();
  reader.onload = function(e) {
    document.querySelector('#image_file_preview').src = e.target.result;
  };
  reader.readAsDataURL(file);
})
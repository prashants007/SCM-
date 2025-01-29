let currentTheme = getTheme();

changeTheme();
function changeTheme(){
    document.querySelector('html').classList.add(currentTheme);
    const changeThemeBtn = document.querySelector('#theme_change_btn');
    changeThemeBtn.querySelector('span').textContent = getTheme();
    changeThemeBtn.addEventListener("click",()=>{
        document.querySelector('html').classList.remove(currentTheme);
        changeThemeBtn.querySelector('span').textContent = currentTheme=='dark'?'light':'dark';
       currentTheme = currentTheme=='dark'?'light':'dark';
       document.querySelector('html').classList.add(currentTheme);
       setTheme(currentTheme);
    })
}

function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    let theme = localStorage.getItem("theme");
   return theme? theme : "light";
}
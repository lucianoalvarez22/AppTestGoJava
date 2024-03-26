document.addEventListener('DOMContentLoaded', function () {
    const toggleModoOscuro = document.getElementById('toggleModoOscuro');

    toggleModoOscuro.addEventListener('change', function () {
        document.body.classList.toggle('dark-mode', toggleModoOscuro.checked);
        
        const header = document.querySelector('header');
    	header.classList.toggle('dark-mode');
    	
    	const sectionTextos = document.querySelectorAll('.hero__txt');
    	sectionTextos.forEach(element => {
        element.classList.toggle('dark-mode');
    });
        
        
        localStorage.setItem('modoOscuro', toggleModoOscuro.checked);
    });

    // Carga la preferencia desde localStorage al cargar la página
    const modoOscuroGuardado = localStorage.getItem('modoOscuro');
    if (modoOscuroGuardado === 'true') {
        toggleModoOscuro.checked = true;
        document.body.classList.add('dark-mode');
    }
});


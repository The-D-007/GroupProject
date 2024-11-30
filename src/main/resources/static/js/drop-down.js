document.addEventListener('DOMContentLoaded', (event) => {
    const collapsibleHeaders = document.querySelectorAll('.collapsible-header');

    collapsibleHeaders.forEach(header => {
        const collapsibleBtn = header.querySelector('.collapsible-btn');
        const collapsibleContent = header.nextElementSibling;

        header.addEventListener('click', () => {
            const isOpen = collapsibleContent.style.display === 'block';
            collapsibleContent.style.display = isOpen ? 'none' : 'block';
            collapsibleBtn.textContent = isOpen ? 'arrow_right' : 'arrow_drop_down';
        });
    });

    window.addEventListener('click', (event) => {
        if (!event.target.closest('.collapsible-header')) {
            collapsibleHeaders.forEach(header => {
                const collapsibleContent = header.nextElementSibling;
                const collapsibleBtn = header.querySelector('.collapsible-btn');
                collapsibleContent.style.display = 'none';
                collapsibleBtn.textContent = 'arrow_right';
            });
        }
    });
});
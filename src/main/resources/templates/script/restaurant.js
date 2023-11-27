document.addEventListener("DOMContentLoaded", function () {
    const uploadButton = document.getElementById("uploadButton");
    const fileInput = document.getElementById("photo-upload");

    uploadButton.addEventListener("click", async function () {
        const photoFile = fileInput.files[0]; // Получаем выбранный файл

        if (!photoFile) {
            alert("Please select a file");
            return;
        }

        const formData = new FormData();
        formData.append("photo", photoFile);

        try {
            const response = await fetch("http://localhost:8080/photo/upload", {
                method: 'POST',
                body: formData, // Отправляем файл как FormData
            });

            if (response.ok) {
                alert("Success");
            } else {
                console.error('Upload failed.');
            }
        } catch (error) {
            console.error('Network error:', error);
        }
    });
});

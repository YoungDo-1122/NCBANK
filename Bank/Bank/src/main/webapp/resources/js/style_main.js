/* 캐러셀 스크립트 */

let currentIndex = 0;
const images = document.querySelectorAll('.carousel img');
const totalImages = images.length;

function showImage(index) {
	images.forEach((img, i) => {
		img.classList.toggle('active', i === index);
	});
}

function showNextImage() {
	currentIndex = (currentIndex + 1) % totalImages;
	showImage(currentIndex);
}

function showPrevImage() {
	currentIndex = (currentIndex - 1 + totalImages) % totalImages;
	showImage(currentIndex);
}

document.querySelector('.carousel-button.prev').addEventListener('click', showPrevImage);
document.querySelector('.carousel-button.next').addEventListener('click', showNextImage);

setInterval(showNextImage, 3000);
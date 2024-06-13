
document.addEventListener('DOMContentLoaded', () => {
    const exchangeRates = {
        EUR: 0.85,  // 1 USD = 0.85 EUR
        KRW: 1200,  // 1 USD = 1200 KRW
        JPY: 110,   // 1 USD = 110 JPY
    };

    document.getElementById('convertBtn').addEventListener('click', () => {
        const amount = document.getElementById('amount').value;
        const currency = document.getElementById('currency').value;

        if (amount === '' || isNaN(amount)) {
            alert('유효한 금액을 입력하세요.');
            return;
        }

        const rate = exchangeRates[currency];

        if (!rate) {
            alert('해당 통화에 대한 환율 정보를 가져올 수 없습니다.');
            return;
        }

        const convertedAmount = (amount * rate).toFixed(2);
        document.getElementById('result').textContent = `${amount} USD는 ${convertedAmount} ${currency}입니다.`;
    });
});

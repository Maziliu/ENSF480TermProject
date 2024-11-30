import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Notification.css';
import '../styles/CancellationNotification.css';

const CancellationNotification = ({ refundData }) => {
  const [showPopup, setShowPopup] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (refundData && refundData.refund) {
      setShowPopup(true);
    }
  }, [refundData]);

  const handleClose = () => {
    setShowPopup(false);
    navigate('/');
  };

  const handlePrint = () => {
    window.print();
  };

  //use refundData to check if there's a discount code
  const hasDiscountCode = refundData.creditDiscountCode;

  return (
    <>
      {showPopup && (
        <div className="notification-popup">
          <div className="popup-content">
            <h4>Your booking has been successfully cancelled</h4>
            <button className="close-btn" onClick={handleClose}>
              <span className="close-icon">×</span>
            </button>
            <div className="ticket">
              <h4 className="refund-header">Cancellation Details</h4>
              <div className="refund-line">
                <b>Refunded Transaction ID:</b>
                <span>{refundData.transaction.refundedTransactionId || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>User Email:</b>
                <span>{refundData.transaction.userEmail || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Transaction Amount:</b>
                <span>${refundData.transaction.transactionAmount.toFixed(2)}</span>
              </div>
              <div className="refund-line">
                <b>Transaction Date:</b>
                <span>{new Date(refundData.transaction.transactionDateTime).toLocaleString()}</span>
              </div>
              <div className="refund-line">
                <b>Booking ID:</b>
                <span>{refundData.transaction.transactionId || 'N/A'}</span>
              </div>
            </div>
            <div className="refund">
              <h4 className="refund-header">Refund Details</h4>
              <div className="refund-line">
                <b>Refunded Amount:</b>
                <span>${(refundData.transaction.transactionAmount * 0.85).toFixed(2)}</span>
              </div>
              <div className="refund-line">
                <b>Refund Status:</b>
                <span>{refundData.refundStatus || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Transaction ID:</b>
                <span>{refundData.transaction.transactionId || 'N/A'}</span>
              </div>
              <div className="refund-line">
                <b>Refund Date:</b>
                <span>{new Date(refundData.transaction.transactionDateTime).toLocaleString()}</span>
              </div>
              {/* Only display discount code if it exists */}
              {hasDiscountCode && (
                <div className="refund-line">
                  <b>Credit Discount Code:</b>
                  <span className="discount-code" onClick={() => {navigator.clipboard.writeText(refundData.creditDiscountCode.code)}}>{refundData.creditDiscountCode.code || 'N/A'}</span>
                </div>
              )}
              {hasDiscountCode && (
                <div className="refund-line">
                  <b>Credit Discount Code Expiry Date:</b>
                  <span>{new Date(refundData.creditDiscountCode.expireDate).toString() || 'N/A'}</span>
                </div>
              )}
            </div>
            <button className="print-btn" onClick={handlePrint}>
              Print Cancellation Details
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default CancellationNotification;





// return (
//   <>
//     {showPopup && (
//       <div className="notification-popup">
//         <div className="popup-content">
//           <h4>Your booking has been successfully cancelled</h4>
//           <button className="close-btn" onClick={handleClose}>
//             <span className="close-icon">×</span>
//           </button>
//           <div className="ticket">
//             <h4 className="refund-header">Cancellation Details</h4>
//             <div className="refund-line">
//               <b>Movie:</b>
//               <span>{refundData.movie || 'N/A'}</span>
//             </div>
//             <div className="refund-line">
//               <b>Theatre:</b>
//               <span>{refundData.theatre || 'N/A'}</span>
//             </div>
//             <div className="refund-line">
//               <b>Seat:</b>
//               <span>{refundData.seatName || 'N/A'}</span>
//             </div>
//             <div className="refund-line">
//               <b>Showtime:</b>
//               <span>{refundData.showtime.time ? new Date(cancellationData.showtime.time).toLocaleString() : 'N/A'}</span>
//             </div>
//             <div className="refund-line">
//               <b>Booking ID:</b>
//               <span>{refundData.bookingId ? cancellationData.bookingId : 'N/A'}</span>
//             </div>
//           </div>
//           <div className="refund">
//             <h4 className="refund-header">Refund Details</h4>
//             <div className="refund-line">
//               <b>Refunded Amount:</b>
//               <span>${refundData.refundedAmount ? cancellationData.refundedAmount.toFixed(2) : 'N/A'}</span>
//             </div>
//           </div>
//           {userIsRegistered ? (
//             <div className="refund-line">
//               <b>Thank you for being a valued member!</b>
//             </div>
//           ) : (
//             <div className="unregistered-refund">
//               <h4 className="offer-header">Special Offer</h4>
//               <div className="refund-line">
//                 <b>Use this code for future purchases (expiry date: {new Date(new Date().setFullYear(new Date().getFullYear() + 1)).toLocaleDateString()}):</b>
//                 <span>{refundData.discountCode}</span>
//               </div>
//             </div>
//           )}
//           <button className="print-btn" onClick={handlePrint}>
//             Print Cancellation Details
//           </button>
//         </div>
//       </div>
//     )}
//   </>
// );
// };
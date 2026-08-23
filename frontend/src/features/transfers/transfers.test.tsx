import {describe, expect, it, vi} from 'vitest';
import {fireEvent, render, screen} from '@testing-library/react';
import TransferPage from './TransferPage';

vi.mock('../../api/client', () => ({
  api: {
    get: vi.fn((path: string) => Promise.resolve({data: path === '/accounts' ? [{id: 'acc-1', name: 'Everyday', balance: 100, type: 'Current', number: '1'}] : path === '/beneficiaries' ? [{id: 'ben-1', name: 'Maya', status: 'VERIFIED', bank: 'ANSH', accountNumber: '2'}] : []})),
    post: vi.fn(() => Promise.resolve({data: {}})),
  },
}));

describe('TransferPage', () => {
  it('submits a positive transfer through the API client', async () => {
    render(<TransferPage />);
    const input = await screen.findByLabelText('Transfer amount');
    fireEvent.change(input, {target: {value: '10'}});
    fireEvent.click(screen.getByRole('button', {name: /review & send/i}));
    expect(await screen.findByText('Transfer sent.')).toBeTruthy();
  });
});
